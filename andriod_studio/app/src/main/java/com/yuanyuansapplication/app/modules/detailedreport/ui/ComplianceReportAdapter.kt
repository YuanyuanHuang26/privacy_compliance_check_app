package com.yuanyuansapplication.app.modules.detailedreport.ui
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuanyuansapplication.app.databinding.ItemComplianceQuestionBinding

class ComplianceReportAdapter(
    private var complianceData: List<Pair<String, String>>
) : RecyclerView.Adapter<ComplianceReportAdapter.ComplianceReportViewHolder>() {

    inner class ComplianceReportViewHolder(
        private val binding: ItemComplianceQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pair<String, String>) {
            binding.question = item.first
            binding.answer = item.second
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplianceReportViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemComplianceQuestionBinding.inflate(layoutInflater, parent, false)
        return ComplianceReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplianceReportViewHolder, position: Int) {
        holder.bind(complianceData[position])
    }

    override fun getItemCount(): Int = complianceData.size

    fun updateData(newData: List<Pair<String, String>>) {
        complianceData = newData
        notifyDataSetChanged()
    }
}
