package com.nextack.tenorsearcher.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AbsListView
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nextack.tenorsearcher.R
import com.nextack.tenorsearcher.application.TenorSearcherApplication
import com.nextack.tenorsearcher.constants.CommonConstants
import com.nextack.tenorsearcher.constants.RestConstants
import com.nextack.tenorsearcher.rest.responses.RestResponse
import com.nextack.tenorsearcher.rest.responses.SearchResult
import com.nextack.tenorsearcher.view.OnFragmentInteractionListener
import com.nextack.tenorsearcher.view.activities.MainActivity
import com.nextack.tenorsearcher.view.adapters.GifGridAdapter
import com.nextack.tenorsearcher.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_trending.*
import timber.log.Timber


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
    private lateinit var mainViewModel: MainViewModel

    private var enableNextRequests: Boolean = true

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
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gifGridAdapter = GifGridAdapter(context!!)
        grid_view.adapter = gifGridAdapter

        grid_view.onItemClickListener = (object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var bundle = Bundle()
                bundle.putString(CommonConstants.BUNDLE_GIF_URL, gifGridAdapter.urlList[position])
                Navigation.findNavController(view!!).navigate(R.id.action_select_gif, bundle)
            }
        })

        search_field.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                grid_view.smoothScrollToPosition(0)
                mainViewModel.search(search_field.text.toString())
            }
            true
        }

        grid_view.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                //Timber.d("firstVisibleItem: $firstVisibleItem")
                //Timber.d("visibleItemCount: $visibleItemCount")
                //Timber.d("totalItemCount: $totalItemCount")

                mainViewModel.firstVisibleItem = firstVisibleItem

                if(enableNextRequests &&
                    firstVisibleItem > 0 &&
                    totalItemCount > 0 &&
                    firstVisibleItem + RestConstants.GIF_LIMIT / 2 > totalItemCount) {
                    enableNextRequests = false
                    mainViewModel.next()
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }
        })


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainViewModel = ViewModelProviders.of(this, (activity as MainActivity).viewModeFactory).get(MainViewModel::class.java)

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

        mainViewModel.restResult.observe(this, Observer {
            enableNextRequests = true
            handleResponse(it, !it.isNext)
        })

        mainViewModel.getTrending()

        mainViewModel.searchString.observe(this, Observer {
            search_field.setText(it)
        })
    }

    private fun handleResponse(restResponse: RestResponse<SearchResult>, isFullUpdate: Boolean) {
        if(restResponse.isSuccessful()) {
            if(isFullUpdate) {
                enableNextRequests = true
                mainViewModel.firstVisibleItem = 0
                gifGridAdapter.update(restResponse.body?.toPreviewUrlList())
            } else {
                gifGridAdapter.add(restResponse.body?.toPreviewUrlList())
            }

        } else {
            (activity as MainActivity).toastUtils.showToast(restResponse.errorMessage!!)
        }
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
