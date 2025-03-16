/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2025-present Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

@file:OptIn(ExperimentalUuidApi::class)

package org.jraf.bwm.shared.messaging

import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic
import kotlinx.serialization.json.encodeToDynamic
import org.jraf.bwm.shared.model.SavedWindow
import kotlin.js.Promise
import kotlin.uuid.ExperimentalUuidApi

class Messenger {
  private fun sendMessage(message: Message): Promise<Any?> {
    return chrome.runtime.sendMessage(Json.encodeToDynamic(message))
  }

  suspend fun sendGetSavedWindowsMessage(): List<SavedWindow> {
    val response = sendMessage(GetSavedWindowsMessage).await()
    console.log("Got response %o", response)
    val getSavedWindowsResponse = Json.decodeFromDynamic<GetSavedWindowsResponse>(response)
    return getSavedWindowsResponse.savedWindows
  }

  fun sendOpenOrFocusSavedWindowMessage(savedWindowId: String) {
    val message = OpenOrFocusSavedWindowMessage(savedWindowId)
    sendMessage(message)
  }
}

fun Any.asMessage(): Message {
  return Json.decodeFromDynamic(this)
}
