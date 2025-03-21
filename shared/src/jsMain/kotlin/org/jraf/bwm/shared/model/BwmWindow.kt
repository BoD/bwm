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

package org.jraf.bwm.shared.model

import kotlinx.serialization.Serializable

// These are serializable because they're passed around via messages
@Serializable
data class BwmWindow(
  val id: String,

  /**
   * If a window has a name it is saved.
   */
  val name: String?,

  /**
   * If a window has a system window id it is bound.
   */
  val systemWindowId: Int?,

  val focused: Boolean,

  val tabs: List<BwmTab>,
) {
  val isSaved: Boolean
    get() = name != null

  val isBound: Boolean
    get() = systemWindowId != null

  companion object
}

@Serializable
data class BwmTab(
  val title: String,
  val url: String,
  val favIconUrl: String?,
  val active: Boolean,
)
