package com.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonNotificationClickListener {
    private lateinit var viewModel: DetailViewModel
    private var stuPos = ""
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            stuPos = StudentDetailFragmentArgs.fromBundle(requireArguments()).stuPost
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(stuPos)
        dataBinding.listener = this
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            //var student = it
            dataBinding.student = it
//            txtID.setText(it.id)
//            txtName.setText(it.name)
//            txtBod.setText(it.bod)
//            txtPhone.setText(it.phone)
//            imageView2.loadImage(it.photoUrl, progressBar2)

//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_account_circle_24)
//                    }
//            }
        })
    }

    override fun onButtonNotificationClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(v.tag.toString(),
                    "A new notification created",
                    R.drawable.ic_baseline_account_circle_24)
            }
    }

}