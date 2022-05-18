package me.yptang.funny.tools

import android.util.Base64
import android.util.Log
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * AES解密
 */
object AESCrypt {

    private val keySpec = SecretKeySpec("cretinzp**273846".toByteArray(), "AES")

    fun decrypt(encrypt: String): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, keySpec)
        return try {
            String(cipher.doFinal(Base64.decode(encrypt, Base64.NO_WRAP)))
        } catch (e: Exception) {
            Log.e("AESCrypt", "解密失败, cause:${e.cause}")
            encrypt
        }
    }

}