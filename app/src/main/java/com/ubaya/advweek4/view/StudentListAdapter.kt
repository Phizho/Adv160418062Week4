package com.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.StudentListItemBinding
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.*


class StudentListAdapter(val studenList: ArrayList<Student>) :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)

        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studenList[position]
        holder.view.listener = this
//        holder.view.txtId.text = studenList[position].id
//        holder.view.txtName.text = studenList[position].name
//        holder.view.imageView.loadImage(studenList[position].photoUrl.toString(), holder.view.progressBar)
//
//        holder.view.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(studenList[position].id!!)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return studenList.size
    }

    fun updateStudentList(newStudentList: List<Student>) {
        studenList.clear()
        studenList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}