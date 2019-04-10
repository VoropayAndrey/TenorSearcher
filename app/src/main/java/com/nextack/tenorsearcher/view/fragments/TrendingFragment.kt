package com.nextack.tenorsearcher.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.nextack.tenorsearcher.R
import com.nextack.tenorsearcher.application.TenorSearcherApplication
import com.nextack.tenorsearcher.repository.Repository
import com.nextack.tenorsearcher.view.OnFragmentInteractionListener
import com.nextack.tenorsearcher.view.adapters.GifGridAdapter
import kotlinx.android.synthetic.main.fragment_trending.*
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TrendingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TrendingFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TrendingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var gifGridAdapter: GifGridAdapter

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.activity?.application as TenorSearcherApplication).getApplicationComponent().inject(this)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_trending, container, false)
        gifGridAdapter = GifGridAdapter(context!!)
        view.findViewById<GridView>(R.id.grid_view).adapter = gifGridAdapter
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        //repository.getAnonId()
        repository.trending().observe(this, Observer {
            gifGridAdapter.update(it.body?.toUrlList())
            //var url = it.body?.results?.get(0)?.media?.get(0)?.get("gif")?.get("url").toString()
            //Glide.with(context!!).load(url).into(gifExample)

        })
        //repository.search("cats")
    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrendingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrendingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
