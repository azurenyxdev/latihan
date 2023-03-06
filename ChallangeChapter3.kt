

abstract class DeklarasiGame {
    abstract fun deklarasiArena()
    abstract fun deklarasiPemain(pemain1: Pemain, pemain2: Pemain)
    abstract fun mulaiGame()
    abstract fun hasil()
}

class Pemain {
    private var poin = 0
    private var status = "KALAH"

    fun getPoin(): Int = poin

    fun setPoin(jawabPemain: String){
        if (jawabPemain.uppercase() == "BATU"){
            poin = 2
        }
        else if (jawabPemain.uppercase() == "GUNTING"){
            poin = 1
        }
        else if (jawabPemain.uppercase() == "KERTAS"){
            poin = 0
        }
        else{
            poin = -1
        }
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getStatus(): String = status
}

class GameBuilder : DeklarasiGame() {
    private lateinit var pemain1: Pemain
    private lateinit var pemain2: Pemain
    private var inputValid = false

    override fun deklarasiArena() {
        println("============================")
        println("GAME SUIT TERMINAL VERSION")
        println("============================")
    }

    override fun deklarasiPemain(pemain1: Pemain, pemain2: Pemain) {
        this.pemain1 = pemain1
        this.pemain2 = pemain2

        print("1. Masukan jawaban pemain 1:")
        this.pemain1.setPoin(readLine()!!)
        print("2. Masukan jawaban pemain 2:")
        this.pemain2.setPoin(readLine()!!)
    }

    override fun mulaiGame() {
        val poinPemain1 = pemain1.getPoin()
        val poinPemain2 = pemain2.getPoin()
        val hitungPoin = poinPemain1 - poinPemain2

        if (poinPemain1 != -1 && poinPemain2 != -1){
            if (hitungPoin == 1 || hitungPoin == -2){
                pemain1.setStatus("MENANG")
            }
            else if(hitungPoin == 2 || hitungPoin == -1){
                pemain2.setStatus("MENANG")
            }
            else{
                pemain1.setStatus("DRAW")
                pemain2.setStatus("DRAW")
            }
            inputValid = true
        }
        else{
            println("\nHasil : \nInput Tidak Valid")
            inputValid = false
        }
    }

    override fun hasil() {
        if(inputValid){
            if (pemain1.getStatus() == "MENANG"){
                println("\nHasil : \nPemain 1 MENANG!")
            }
            else if (pemain2.getStatus() == "MENANG"){
                println("\nHasil : \nPemain 2 MENANG!")
            }
            else{
                println("\nHasil : \nDRAW!")
            }
        }
    }
}

fun main() {
    val pemain1 = Pemain()
    val pemain2 = Pemain()
    val gameSuit = GameBuilder()
    var ulangGame = false
    var pilihUlangGame : Char
    var ulangGameInvalid : Boolean

    do {
        gameSuit.deklarasiArena()
        gameSuit.deklarasiPemain(pemain1, pemain2)
        gameSuit.mulaiGame()
        gameSuit.hasil()

        do {
            print("\nMain Lagi [y/n] : ")
            pilihUlangGame = readLine()!![0]

            if (pilihUlangGame.lowercaseChar() == 'y'){
                ulangGame = true
                ulangGameInvalid = false
            }
            else if (pilihUlangGame.lowercaseChar() == 'n'){
                ulangGame = false
                ulangGameInvalid = false
            }
            else{
                ulangGameInvalid = true
            }
        } while (ulangGameInvalid)

    } while (ulangGame)
}