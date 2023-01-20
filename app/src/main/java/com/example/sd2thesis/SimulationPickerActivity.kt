package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class SimulationPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation_picker)

        /** Initializing Variables **/
        val simulReturn = findViewById<AppCompatButton>(R.id.btn_simul_picker_return)
        val a = findViewById<AppCompatButton>(R.id.btn_a)
        val b = findViewById<AppCompatButton>(R.id.btn_b)
        val c = findViewById<AppCompatButton>(R.id.btn_c)
        val d = findViewById<AppCompatButton>(R.id.btn_d)
        val e = findViewById<AppCompatButton>(R.id.btn_e)
        val f = findViewById<AppCompatButton>(R.id.btn_f)
        val g = findViewById<AppCompatButton>(R.id.btn_g)
        val h = findViewById<AppCompatButton>(R.id.btn_h)
        val i = findViewById<AppCompatButton>(R.id.btn_i)
        val j = findViewById<AppCompatButton>(R.id.btn_j)
        val k = findViewById<AppCompatButton>(R.id.btn_k)
        val l = findViewById<AppCompatButton>(R.id.btn_l)
        val m = findViewById<AppCompatButton>(R.id.btn_m)
        val n = findViewById<AppCompatButton>(R.id.btn_n)
        val ñ = findViewById<AppCompatButton>(R.id.btn_ñ)
        val ng = findViewById<AppCompatButton>(R.id.btn_ng)
        val o = findViewById<AppCompatButton>(R.id.btn_o)
        val p = findViewById<AppCompatButton>(R.id.btn_p)
        val q = findViewById<AppCompatButton>(R.id.btn_q)
        val r = findViewById<AppCompatButton>(R.id.btn_r)
        val s = findViewById<AppCompatButton>(R.id.btn_s)
        val t = findViewById<AppCompatButton>(R.id.btn_t)
        val u = findViewById<AppCompatButton>(R.id.btn_u)
        val v = findViewById<AppCompatButton>(R.id.btn_v)
        val w = findViewById<AppCompatButton>(R.id.btn_w)
        val x = findViewById<AppCompatButton>(R.id.btn_x)
        val y = findViewById<AppCompatButton>(R.id.btn_y)
        val z = findViewById<AppCompatButton>(R.id.btn_z)

        /** intent variable **/
        var intent: Intent?

        /** This Variable contains what simulation letter should play **/
        var stringToPass: String

        /** Redirect to Dashboard **/
        simulReturn.setOnClickListener {
            intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** !! is for null safety **/

        /** A **/
        a.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "A"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** B **/
        b.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "B"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** C **/
        c.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "C"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** D **/
        d.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "D"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** E **/
        e.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "E"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** F **/
        f.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "F"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** G **/
        g.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "G"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** H **/
        h.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "H"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** I **/
        i.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "I"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** J **/
        j.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "J"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** K **/
        k.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "K"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** L **/
        l.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "L"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** M **/
        m.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "M"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** N **/
        n.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "N"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** Ñ **/
        ñ.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "Ñ"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** NG **/
        ng.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "NG"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** O **/
        o.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "O"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** P **/
        p.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "P"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** Q **/
        q.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "Q"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** R **/
        r.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "R"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** S **/
        s.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "S"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** T **/
        t.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "T"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** U **/
        u.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "U"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** V **/
        v.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "V"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** W **/
        w.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "W"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** X **/
        x.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "X"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** Y **/
        y.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "Y"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }

        /** Z **/
        z.setOnClickListener {
            intent = Intent( this, SimulationActivity::class.java)
            stringToPass = "Z"
            intent!!.putExtra("ButtonClicked", stringToPass)

            startActivity(intent)
        }
    }
}