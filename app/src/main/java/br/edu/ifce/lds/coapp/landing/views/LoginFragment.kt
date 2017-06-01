package br.edu.ifce.lds.coapp.landing.views

import br.edu.ifce.lds.coapp.R.layout.fragment_login
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.onClick


/**
 * Login fragment to be loaded in the main landing screen
 */
class LoginFragment : android.support.v4.app.Fragment() {


    lateinit var mCallback: ILoginFragmentCallbacks

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?,
                              savedInstanceState: android.os.Bundle?): android.view.View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(fragment_login, container, false)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.onClick {
            mCallback.onClickBackButton()
        }
    }

    interface ILoginFragmentCallbacks {
        fun onClickBackButton()
    }

    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)
        try {
            mCallback = context as ILoginFragmentCallbacks
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ILoginFragmentCallbacks");
        }
    }

}// Required empty public constructor
