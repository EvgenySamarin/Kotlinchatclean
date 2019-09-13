package ru.dvc.kotlin_chat_clean.presentation.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

/**
 * @param VH тип контента, который будет находиться в адаптере
 * Для выделения поведения списков.
 */
abstract class BaseAdapter<VH : BaseAdapter.BaseViewHolder> : RecyclerView.Adapter<VH>() {

    var items: ArrayList<Any> = ArrayList() //элементы списка

    var onClick: OnClick? = null //интерфейс экземпляр

    /** id макета элемента списка. Будет имплементирован в дочерних классах. Тип: Int. */
    abstract val layoutRes: Int

    /** абстрактная функция, создающая наполнитель */
    abstract fun createHolder(view: View, viewType: Int): VH


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position)) //наполняем список контентом из элемента

        holder.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return createHolder(v, viewType)
    }

    /** возвращает из списка элемент */
    fun getItem(position: Int): Any = items[position]

    /** добавляет элемент в список */
    fun add(newItem: Any) { items.add(newItem) }

    /** добавляет элементы в список */
    fun add(newItems: List<Any>) { items.addAll(newItems) }

    /** очищает список */
    fun clear() = items.clear()

    /** сеттер для поля onClick
     * Принимает функции для обычного: click: (Any?, View) -> Unit
     * И длинного нажатия: longClick: (Any?, View) -> Unit = { _, _ -> } по умолчанию ничего не делает
     */
    fun setOnClick(click: (Any?, View) -> Unit, longClick: (Any?, View) -> Unit = { _, _ -> }) {
        onClick = object : OnClick {
            override fun onClick(item: Any?, view: View) {
                Timber.d("onClick")
                click(item, view)
            }

            override fun onLongClick(item: Any?, view: View) {
                longClick(item, view)
            }
        }
    }

    /** слушатель нажатий на элементы списка который можно будет подсунуть для элементов списка */
    interface OnClick {
        fun onClick(item: Any?, view: View)
        fun onLongClick(item: Any?, view: View)
    }

    /** Для выделения поведения вьюхолдеров. */
    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view) {
        var onClick: OnClick? = null //экземпляр интерфейса
        var item: Any? = null //элемент списка

        init {
            view.setOnClickListener {
//если присвоен слушатель, обрабатываем его указав при этом элемент для взаимодействия
                onClick?.onClick(item, it)
            }
            view.setOnLongClickListener {
                onClick?.onLongClick(item, it)
                true
            }
        }

        /** абстрактная функция, заполняющая макет элемента списка данными */
        protected abstract fun onBind(item: Any)

        /** присваивает элемент списка вьюхолдеру. Делегирует заполнение вьюхолдера функции */
        fun bind(item: Any) {
            this.item = item

            onBind(item)
        }

    }
}

