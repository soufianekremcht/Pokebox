package com.soufianekre.pokebox.data.network

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.network.APIConstants.REST_URL
import com.squareup.moshi.*
import okhttp3.OkHttpClient
import okio.IOException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type

object RestProvider {

    private var okHttpClient: OkHttpClient? = null

    // Sample Custom Json adapter for list of json objects
    val CUSTOM_FACTORY: JsonAdapter.Factory = object : JsonAdapter.Factory {
        @Nullable
        override fun create(
            @NonNull type: Type,
            @NonNull annotations: Set<Annotation?>,
            @NonNull moshi: Moshi): JsonAdapter<List<PokemonItemInfo?>?>? {

            val adapter: JsonAdapter<List<PokemonItemInfo>> = moshi.nextAdapter(this, type, annotations)
            if (annotations.isNotEmpty()) return null
            if (type == Types.newParameterizedType(
                    MutableList::class.java,
                    PokemonItemInfo::class.java)) {

                return object : JsonAdapter<List<PokemonItemInfo?>?>() {
                    @Nullable
                    @Throws(IOException::class)
                    override fun fromJson(reader: JsonReader?): List<PokemonItemInfo?>? {

                        return adapter.fromJson(reader)
                    }

                    @Throws(IOException::class)
                    override fun toJson(
                        writer: JsonWriter?,
                        @Nullable value: List<PokemonItemInfo?>?
                    ) {
                    }
                }
            } else return null
        }
    }


    val moshi = Moshi.Builder()
        .build()



    fun provideOkHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            var client: OkHttpClient.Builder = OkHttpClient.Builder()
            okHttpClient = client.build()
        }
        return okHttpClient!!
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(REST_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getPokemonService(): PokemonApiService {
        return provideRetrofit().create(PokemonApiService::class.java)
    }


}