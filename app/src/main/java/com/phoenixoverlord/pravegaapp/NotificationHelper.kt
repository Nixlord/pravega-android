/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.phoenixoverlord.pravegaapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import java.lang.System.currentTimeMillis

class NotificationHelper(private val context: Context) {
  companion object {
    private const val CHANNEL_QUOTES = "quotes"
    private const val REQUEST_CONTENT = 1
    private const val REQUEST_BUBBLE = 2
    private const val NOTIFICATION_ID = 0
  }

  private val notificationManager = context.getSystemService(NotificationManager::class.java)

  fun setUpNotificationChannels() {
    if (notificationManager?.getNotificationChannel(
            CHANNEL_QUOTES) == null) {
      val channel = NotificationChannel(
          CHANNEL_QUOTES,
          "Inspirational Quotes",
          NotificationManager.IMPORTANCE_HIGH
      ).apply {
        description = "Inspirational Quotes Channel"
      }
      notificationManager?.createNotificationChannel(channel)
    }
  }

  fun showNotification(fromUser: Boolean) {

    // Create Icon
    val icon = createIcon()

    // Create the Contact
    val person = createPerson(icon)

    // Create the Notification
    val notification = createNotification(icon, person)

    // Create the Bubble's Metadata
    val bubbleMetaData = createBubbleMetadata(icon, fromUser)

    // Set the bubble metadata
    notification.setBubbleMetadata(bubbleMetaData)

    // Build and Display the Notification
    notificationManager?.notify(NOTIFICATION_ID, notification.build())
  }

  fun createPerson(icon: Icon): Person {
    return Person.Builder()
        .setName("Code Coach")
        .setIcon(icon)
        .setBot(true)
        .setImportant(true)
        .build()
  }

  fun createIcon() = Icon.createWithResource(context, R.drawable.ic_launcher_foreground)

  fun createBubbleMetadata(icon: Icon, fromUser: Boolean):
      Notification.BubbleMetadata {
    return Notification.BubbleMetadata.Builder()
        .setDesiredHeight(120)
        .setIcon(icon)
        .apply {
          if (fromUser) {
            setAutoExpandBubble(true)
            setSuppressNotification(true)
          }
        }
        .setIntent(
            createIntent(REQUEST_BUBBLE)
        )
        .build()
  }

  fun createIntent(requestCode: Int): PendingIntent {
    return PendingIntent.getActivity(
        context,
        requestCode,
        Intent(context, SensorActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT
    )
  }

  fun createNotification(icon: Icon, person: Person): Notification.Builder {
    return Notification.Builder(context, CHANNEL_QUOTES)
        .setContentTitle("Code Coach")
        .setLargeIcon(icon)
        .setSmallIcon(icon)
        .setCategory(Notification.CATEGORY_MESSAGE)
        .setStyle(Notification.MessagingStyle(person)
            .setGroupConversation(false)
            .addMessage("Shibasis Patnaik is a human", currentTimeMillis(), person)
        )
        .addPerson(person)
        .setShowWhen(true)
        .setContentIntent(createIntent(REQUEST_CONTENT))
  }
}