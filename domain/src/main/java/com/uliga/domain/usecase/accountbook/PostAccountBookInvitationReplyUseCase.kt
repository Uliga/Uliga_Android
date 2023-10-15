package com.uliga.domain.usecase.accountbook

import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostAccountBookInvitationReplyUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(accountBookInvitationReply: AccountBookInvitationReply): Result<AccountBookInvitationReply> {
        return accountBookRepository.postAccountBookInvitationReply(accountBookInvitationReply)
    }
}