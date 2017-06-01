package br.edu.ifce.lds.coapp.landing


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.edu.ifce.lds.coapp.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.onClick


/**
 * Login fragment to be loaded in the main landing screen
 */
class LoginFragment : Fragment() {


    lateinit var mCallback: ILoginFragmentCallbacks

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.onClick {
            mCallback.onClickBackButton()
        }
    }

    interface ILoginFragmentCallbacks {
        fun onClickBackButton()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallback = context as ILoginFragmentCallbacks
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ILoginFragmentCallbacks");
        }
    }

}// Required empty public constructor
