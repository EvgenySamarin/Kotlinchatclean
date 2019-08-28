package ru.dvc.kotlin_chat_clean.presentation.injection

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


//нужен для обозначения viewModel классов при их binding е
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)