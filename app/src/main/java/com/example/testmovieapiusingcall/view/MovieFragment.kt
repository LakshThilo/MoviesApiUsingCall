package com.example.testmovieapiusingcall.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmovieapiusingcall.model.MovieItem
import com.example.testmovieapiusingcall.view.adapters.MovieRecyclerViewAdapter
import com.example.testmovieapiusingcall.databinding.MovieRecyclerviewLayoutBinding
import java.lang.Exception

private const val TAG = "onListFragment: "

class MovieFragment : Fragment() {

    companion object {

        private const val EXTRA_DATA_SET = "EXTRA_DATA_SET"


        fun newInstance(dataSet: ArrayList<MovieItem>) =
           MovieFragment().apply {
               arguments = Bundle().apply {
                    Log.d(TAG, "onResponse: $dataSet")
                    //Toast.makeText(activity,"${dataSet}", Toast.LENGTH_SHORT).show()
                   //println(dataSet)
                    putParcelableArrayList(EXTRA_DATA_SET, dataSet as java.util.ArrayList<out Parcelable>)
               }
           }
    }

    private lateinit var binding: MovieRecyclerviewLayoutBinding

    private val adapter : MovieRecyclerViewAdapter by lazy{
        createAdapter()
    }

    private fun createAdapter() : MovieRecyclerViewAdapter{

        return MovieRecyclerViewAdapter(
            clickDetails = :: openDetails,
            dataSet = emptyList()
        )
    }

    private fun openDetails(movieItem: MovieItem) {
        activity?.showDetails(movieItem)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
     View? {

        Log.d(TAG, "onCreateView: ")
         super.onCreateView(inflater, container, savedInstanceState)

        binding = MovieRecyclerviewLayoutBinding.inflate(
            inflater,
            container,
            false)

        arguments?.getParcelableArrayList<MovieItem>(EXTRA_DATA_SET)?.let {
            Log.d(TAG, "onCreateView: $it")
            displayData(it)
        }?: displayError()

        return binding.root
    }

    private fun displayError() {
        Toast.makeText(activity, "Something failed", Toast.LENGTH_SHORT).show()
        throw Exception("There is an error in Android SDK Parcelable bundle")
    }

    private fun displayData(dataSet: ArrayList<MovieItem>) {

        binding.listMovie.layoutManager = LinearLayoutManager(activity)
        binding.listMovie.adapter = adapter

        adapter.dataSet = dataSet
        adapter.notifyDataSetChanged()

    }
}

fun FragmentActivity.showDetails(movieItem: MovieItem){

    Toast.makeText(this,"${movieItem.title}", Toast.LENGTH_SHORT).show()
    Log.d(TAG, "showDetails: ${movieItem.title}")
    /*supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, )*/
}


