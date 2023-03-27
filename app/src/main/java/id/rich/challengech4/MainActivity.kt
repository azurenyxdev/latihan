package id.rich.challengech4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val batuP1 = findViewById<ImageView>(R.id.ib_batup1)
        val kertasP1 = findViewById<ImageView>(R.id.ib_kertasp1)
        val guntingP1 = findViewById<ImageView>(R.id.ib_guntingp1)
        val batuCom = findViewById<ImageView>(R.id.ib_batucom)
        val kertasCom = findViewById<ImageView>(R.id.ib_kertascom)
        val guntingCom = findViewById<ImageView>(R.id.ib_guntingcom)
        val btRefresh = findViewById<ImageView>(R.id.ib_refresh)
        val result = findViewById<ImageView>(R.id.tv_result) as TextView

        val player = Player()
        val com = Computer()
        var isPlayerChoose = false
        var comChoose = 0


        fun restart(){
            batuP1.setBackgroundResource(R.color.white)
            kertasP1.setBackgroundResource(R.color.white)
            guntingP1.setBackgroundResource(R.color.white)
            batuCom.setBackgroundResource(R.color.white)
            kertasCom.setBackgroundResource(R.color.white)
            guntingCom.setBackgroundResource(R.color.white)
            result.setBackgroundResource(R.color.white)
            result.text = "VS"
            result.setTextSize(80F)
            isPlayerChoose = false
            batuP1.isEnabled = true
            kertasP1.isEnabled = true
            guntingP1.isEnabled = true
        }

        fun start(){
            comChoose = Random.nextInt(0,3)
            com.setPoint(comChoose)

            if(com.getPoint() == 2){
                batuCom.setBackgroundResource(R.drawable.background_btnclick)
            }
            if(com.getPoint() == 1) {
                guntingCom.setBackgroundResource(R.drawable.background_btnclick)
            }
            if (com.getPoint() == 0){
                kertasCom.setBackgroundResource(R.drawable.background_btnclick)
            }

            val player1Point = player.getPoint()
            val comPoint = com.getPoint()
            val calculate = player1Point - comPoint

            if (calculate == 1 || calculate == -2){
                player.setStatus("MENANG")
                com.setStatus("KALAH")
            }
            else if(calculate == 2 || calculate == -1){
                player.setStatus("KALAH")
                com.setStatus("MENANG")
            }
            else{
                player.setStatus("DRAW")
                com.setStatus("DRAW")
            }

            if (player.getStatus() == "MENANG"){
                result.setBackgroundResource(R.color.green)
                result.setTextSize(25F)
                result.text = "Pemain 1\nMENANG!"
            }
            else if(com.getStatus() == "MENANG"){
                result.setBackgroundResource(R.color.green)
                result.setTextSize(25F)
                result.text = "Computer\nMENANG!"
            }
            else{
                result.setBackgroundResource(R.color.blue)
                result.setTextSize(35F)
                result.text = "DRAW"
            }
        }

        batuP1.setOnClickListener {
            batuP1.setBackgroundResource(R.drawable.background_btnclick)
            kertasP1.isEnabled = false
            guntingP1.isEnabled= false
            isPlayerChoose = true
            player.setPoint("BATU")
            start()
        }

        kertasP1.setOnClickListener {
            batuP1.isEnabled = false
            kertasP1.setBackgroundResource(R.drawable.background_btnclick)
            guntingP1.isEnabled = false
            isPlayerChoose = true
            player.setPoint("KERTAS")
            start()
        }

        guntingP1.setOnClickListener {
            batuP1.isEnabled = false
            kertasP1.isEnabled = false
            guntingP1.setBackgroundResource(R.drawable.background_btnclick)
            isPlayerChoose = true
            player.setPoint("GUNTING")
            start()
        }

        btRefresh.setOnClickListener {
            restart()
        }
    }
}

class Player {
    private var point = 0
    private var status = "KALAH"

    fun getPoint(): Int = point

    fun setPoint(p1Choosed: String){
        if (p1Choosed == "BATU"){
            point = 2
        }
        else if (p1Choosed == "GUNTING"){
            point = 1
        }
        else if (p1Choosed == "KERTAS"){
            point = 0
        }
        else{
            point = -1
        }
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getStatus(): String = status
}

class Computer {
    private var point = 0
    private var status = "KALAH"

    fun getPoint(): Int = point

    fun setPoint(point: Int){
        this.point = point
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getStatus(): String = status
}