/*
 * Copyright 2021 Brackeys IDE contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brackeys.ui.feature.fonts.internal

import com.brackeys.ui.data.repository.fonts.FontsRepositoryImpl
import com.brackeys.ui.data.storage.database.AppDatabase
import com.brackeys.ui.data.storage.keyvalue.SettingsManager
import com.brackeys.ui.domain.providers.coroutines.DispatcherProvider
import com.brackeys.ui.domain.repository.fonts.FontsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object FontsModule {

    @Provides
    @ViewModelScoped
    fun provideFontsRepository(
        dispatcherProvider: DispatcherProvider,
        settingsManager: SettingsManager,
        appDatabase: AppDatabase
    ): FontsRepository {
        return FontsRepositoryImpl(dispatcherProvider, settingsManager, appDatabase)
    }
}