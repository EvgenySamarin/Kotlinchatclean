package ru.dvc.kotlin_chat_clean.presentation.ui.core

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dvc.kotlin_chat_clean.R
import timber.log.Timber

/** Для выделения поведения фрагментов содержащих список */
abstract class BaseListFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var lm: RecyclerView.LayoutManager

    //можно переопределить в подклассе но другие классы это поле не видят
    protected abstract val viewAdapter: BaseAdapter<*>

    //переопределенный id макета фрагмента. Присвоен макет содержащий список. Тип: Int.
    override val layoutId = R.layout.fragment_list

    /** инициализация базовых компонент */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lm = LinearLayoutManager(context)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = lm
            adapter = viewAdapter
        }
    }

    /** проваливаем функцию на уровень вверх, т.е. устанавливать слушателя нажатий будет потомок */
    protected fun setOnItemClickListener(func: (Any?, View) -> Unit) {
        Timber.d("setOnItemClickListener")
        viewAdapter.setOnClick(func)
    }

    /** проваливаем функцию на уровень вверх, только теперь для длинного нажатия, при этом короткое
     * нажатие не будет работать*/
    protected fun setOnItemLongClickListener(func: (Any?, View) -> Unit) {
        viewAdapter.setOnClick({ _, _ -> }, longClick = func)
    }
}