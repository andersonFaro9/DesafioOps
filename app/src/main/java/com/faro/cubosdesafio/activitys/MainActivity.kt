package com.faro.cubosdesafio.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.faro.cubosdesafio.ListaDasLojasAdapter
import com.faro.cubosdesafio.ListaModel
import com.faro.cubosdesafio.R
import kotlinx.android.synthetic.main.activity_recycleview.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var list = ArrayList<ListaModel>()
    var adapter = ListaDasLojasAdapter(this, list, list)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview)

        supportActionBar?.title = "Movies"

        // tira elevação da borda da actionbar
        supportActionBar?.elevation = 0F
        prepareList(list)

        recView.adapter = adapter
        recView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

    }

    private fun  prepareList(list: ArrayList<ListaModel>) {

        list.add(
            ListaModel(
                "OPS! não foi dessa vez",
                "Eu gostaria dessa oportunidade.",
                0,
                R.drawable.foto_1
            )
        )
        list.add(
            ListaModel(
                "Baixe o aplicativo Guiaci",
                "Um aplicativo que faz propagandas de lojas.",
                0,
                R.drawable.foto_2
            )
        )
        list.add(
            ListaModel(
                "Conheça meus projetos no Github",
                "Um pouco dos projetos que fiz e participei com a comunidade",
                0,
                R.drawable.foto_4
            )
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.search)


        val searchView = menu.findItem(R.id.search).actionView as? SearchView

        searchView?.queryHint = "Digite a loja"

        searchView?.setOnQueryTextListener(this)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


        }


        return super.onOptionsItemSelected(item)
    }


    override fun onQueryTextSubmit(query: String): Boolean {

        val tag = "Script"
        Log.i(tag, "onQueryTextSubmit $query")

        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {

        Log.i("Script", "onQueryTextChange $newText")

        adapter.filter.filter(newText)
        return true
    }


}
