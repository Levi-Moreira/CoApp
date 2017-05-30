package br.edu.ifce.lds.coapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_landing.*
import org.jetbrains.anko.onClick


/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment




        return inflater!!.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

   fun setUpViews(){
        buttonPlans.onClick {
            Toast.makeText(this.context, R.string.meet_ou_plans, Toast.LENGTH_SHORT).show()
        }

        buttonEmergencySchedule.onClick {
            Toast.makeText(this.context, R.string.emergency_sheducling, Toast.LENGTH_SHORT).show()
        }

        buttonContact.onClick {
            Toast.makeText(this.context, R.string.contact_us, Toast.LENGTH_SHORT).show()
        }

        buttonJoin.onClick {
            Toast.makeText(this.context, R.string.sign_up, Toast.LENGTH_SHORT).show()
        }
    }

}// Required empty public constructor
