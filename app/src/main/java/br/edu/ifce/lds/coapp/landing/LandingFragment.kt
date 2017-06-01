package br.edu.ifce.lds.coapp.landing


import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_landing.*
import org.jetbrains.anko.onClick


/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : android.support.v4.app.Fragment() {


    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?,
                              savedInstanceState: android.os.Bundle?): android.view.View? {
        // Inflate the layout for this fragment




        return inflater!!.inflate(br.edu.ifce.lds.coapp.R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

   fun setUpViews(){
        buttonPlans.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.meet_ou_plans, android.widget.Toast.LENGTH_SHORT).show()
        }

        buttonEmergencySchedule.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.emergency_sheducling, android.widget.Toast.LENGTH_SHORT).show()
        }

        buttonContact.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.contact_us, android.widget.Toast.LENGTH_SHORT).show()
        }

        buttonJoin.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.sign_up, android.widget.Toast.LENGTH_SHORT).show()
        }
    }

}// Required empty public constructor
