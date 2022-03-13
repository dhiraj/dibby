package com.traversient.dibby

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakListener
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import leakcanary.LeakCanary


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this


        LeakCanary.config = LeakCanary.config.copy( onHeapAnalyzedListener = FlipperLeakListener() )
        //Load up Flipper
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(InspectorFlipperPlugin(this,DescriptorMapping.withDefaults()))
            client.addPlugin(LeakCanary2FlipperPlugin())
            client.addPlugin(NetworkFlipperPlugin())
//            client.addPlugin(SharedPreferencesFlipperPlugin(this, "my_shared_preference_file"))
            client.start()
        }
    }
    companion object{
        lateinit var instance: Application
            private set
    }
}