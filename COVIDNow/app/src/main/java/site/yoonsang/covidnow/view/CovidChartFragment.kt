package site.yoonsang.covidnow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.covidnow.R
import site.yoonsang.covidnow.databinding.FragmentCovidChartBinding
import site.yoonsang.covidnow.viewmodel.CovidViewModel

@AndroidEntryPoint
class CovidChartFragment : Fragment() {

    private var _binding: FragmentCovidChartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CovidViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_covid_chart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getCovidResponse()

        viewModel.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.covidInfo.observe(viewLifecycleOwner) {
            binding.covidInfo = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}