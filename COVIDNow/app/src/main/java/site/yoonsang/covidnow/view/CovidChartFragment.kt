package site.yoonsang.covidnow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.covidnow.R
import site.yoonsang.covidnow.databinding.FragmentCovidChartBinding
import site.yoonsang.covidnow.model.CovidInfo
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
        viewModel.getRegionCovidResponse()

        viewModel.toastMessage.observe(viewLifecycleOwner) { toast ->
            Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show()
        }

        viewModel.covidInfo.observe(viewLifecycleOwner) { covidInfo ->
            binding.covidInfo = covidInfo
            setPieChart(covidInfo)
        }

        viewModel.regionCovidInfo.observe(viewLifecycleOwner) { regionCovidInfo ->
            binding.regionCovidInfo = regionCovidInfo
        }
    }

    private fun setPieChart(covidInfo: CovidInfo) {
        val entryList = mutableListOf<PieEntry>()
        entryList.add(PieEntry(covidInfo.city1p.toFloat(), covidInfo.city1n))
        entryList.add(PieEntry(covidInfo.city2p.toFloat(), covidInfo.city2n))
        entryList.add(PieEntry(covidInfo.city3p.toFloat(), covidInfo.city3n))
        entryList.add(PieEntry(covidInfo.city4p.toFloat(), covidInfo.city4n))
        entryList.add(PieEntry(covidInfo.city5p.toFloat(), covidInfo.city5n))
        val colorList = mutableListOf<Int>()
        colorList.add(requireContext().getColor(R.color.city1))
        colorList.add(requireContext().getColor(R.color.city2))
        colorList.add(requireContext().getColor(R.color.city3))
        colorList.add(requireContext().getColor(R.color.city4))
        colorList.add(requireContext().getColor(R.color.city5))
        val pieDataSet = PieDataSet(entryList, "시도별 발생현황")
        pieDataSet.apply {
            sliceSpace = 1f
            selectionShift = 0f
            colors = colorList
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return ""
                }
            }
        }
        val pieData = PieData(pieDataSet)
        binding.regionPieChart.apply {
            data = pieData
            isDrawHoleEnabled = true
            holeRadius = 50f
            transparentCircleRadius = 10f
            setDrawEntryLabels(false)
            legend.isEnabled = false
            description = null
            animateY(1000, Easing.EaseOutCubic)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}