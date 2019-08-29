package ru.dvc.kotlin_chat_clean.data.account

import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity
import ru.dvc.kotlin_chat_clean.domain.accout.AccountRepository
import ru.dvc.kotlin_chat_clean.domain.type.*
import java.util.*

/**
 * Класс, содержащий функции взаимодействия с аккаунтом.
 * При этом решает откуда брать данные: из локальной базы или из сети.
 * Содержит: объекты для работы с бд(val accountCache) и  сервером(val accountRemote),
 * функции которые выполняют регистрацию пользователя(fun register(…))
 * и обновление токена(fun updateAcountToken(…)).
 * Нужен Для взаимодействия с аккаунтом в бд и сети.
 */
class AccountRepositoryImpl(
    private val accountRemote: AccountRemote,
    private val accountCache: AccountCache
) : AccountRepository {

    override fun login(email: String, password: String): Either<Failure, AccountEntity> {
        return accountCache.getToken().flatMap {
            accountRemote.login(email, password, it)
        }.onNext {
            accountCache.saveAccount(it)
        }
    }

    override fun logout(): Either<Failure, None> {
        return accountCache.logout()
    }

    override fun register(email: String, name: String, password: String): Either<Failure, None> {
        return accountCache.getToken().flatMap {
            accountRemote.register(email, name, password, it, Calendar.getInstance().timeInMillis)
        }
    }

    override fun forgetPassword(email: String): Either<Failure, None> {
        throw UnsupportedOperationException("Password recovery is not supported")
    }


    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        return accountCache.getCurrentAccount()
    }


    override fun updateAccountToken(token: String): Either<Failure, None> {
        accountCache.saveToken(token)

        return accountCache.getCurrentAccount()
            .flatMap { accountRemote.updateToken(it.id, token, it.token) }
    }

    override fun updateAccountLastSeen(): Either<Failure, None> {
        throw UnsupportedOperationException("Updating last seen is not supported")
    }


    override fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity> {
        throw UnsupportedOperationException("Editing account is not supported")
    }
}