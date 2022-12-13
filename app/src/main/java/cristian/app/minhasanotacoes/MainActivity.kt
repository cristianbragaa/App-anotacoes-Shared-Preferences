package cristian.app.minhasanotacoes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import cristian.app.minhasanotacoes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Arquivo a ser salvo com nome PREF_ANOTAÇÃO, e modo MODE_PRIVATE
    private val preferencias by lazy {
        getSharedPreferences(
            "PREF_ANOTACAO",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Recuperando anotacao
        val anotacaoRecuperada = preferencias.getString("ANOTACAO", "") // "" -> É um valor opcional, caso não tenha valor nenhum, tera um valor vazio
        binding.editAnotacao.setText(anotacaoRecuperada)

        //Salvando anotacao
        binding.fabConfirmar.setOnClickListener {
            val anotacao = binding.editAnotacao.text.toString()
            preferencias.edit()
                .putString("ANOTACAO", anotacao)
                .apply()

            Snackbar.make(
                it,
                "Anotação salva com sucesso",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}