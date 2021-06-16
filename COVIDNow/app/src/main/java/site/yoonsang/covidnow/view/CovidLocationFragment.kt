package site.yoonsang.covidnow.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.covidnow.R
import site.yoonsang.covidnow.databinding.FragmentCovidLocationBinding
import site.yoonsang.covidnow.util.Constants
import site.yoonsang.covidnow.viewmodel.LocationViewModel

@AndroidEntryPoint
class CovidLocationFragment : Fragment() {

    private var _binding: FragmentCovidLocationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LocationViewModel>()
    private val locationManager by lazy { requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            } else {
                showToastMsg("해당 기능을 이용하기 위해선 접근 권한이 필요합니다.")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_covid_location, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationAdapter = LocationAdapter()
        binding.locationRecyclerView.adapter = locationAdapter

        binding.locationMyLocation.setOnClickListener {
            val permissionCheck = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                getLocation()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    requestPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } else {
                    showSettingsDialog()
                }
            }
        }

        viewModel.toastMessage.observe(viewLifecycleOwner) {
            showToastMsg(it)
        }

        viewModel.document.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                locationAdapter.submitData(viewLifecycleOwner.lifecycle, data)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let {
            val x = location.longitude.toString()
            val y = location.latitude.toString()
            viewModel.getLocationResponse(x, y)
        }
    }

    private fun showToastMsg(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage("[설정] -> [권한]에서 다시 권한을 허용할 수 있습니다.")
        builder.setPositiveButton("설정") { dialog, _ ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}