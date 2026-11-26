package com.came.parkare.dashboardapp.ui.utils

import org.apache.ftpserver.ftplet.Authentication
import org.apache.ftpserver.ftplet.User
import org.apache.ftpserver.ftplet.UserManager
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication

/**
 * Accepts the FTP user name in any case ("parkare", "PARKARE" or "Parkare"); the password is
 * still case sensitive. The name typed by the client is replaced by the configured one before
 * the lookup, so the rest of the server keeps working with the configured name.
 */
class CaseInsensitiveUserManager(
    private val delegate: UserManager
) : UserManager by delegate {

    private fun resolveName(name: String?): String? {
        if (name == null) return null
        return delegate.allUserNames.firstOrNull { it.equals(name, ignoreCase = true) } ?: name
    }

    override fun authenticate(authentication: Authentication): User {
        val resolved = if (authentication is UsernamePasswordAuthentication) {
            UsernamePasswordAuthentication(
                resolveName(authentication.username),
                authentication.password,
                authentication.userMetadata
            )
        } else {
            authentication
        }
        return delegate.authenticate(resolved)
    }

    override fun getUserByName(username: String?): User? = delegate.getUserByName(resolveName(username))

    override fun doesExist(username: String?): Boolean = delegate.doesExist(resolveName(username))
}
