package com.huanqi.video.exoplayer;

import android.content.Context;
import android.os.Handler;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;

import java.util.ArrayList;

/**
 * Exoplayer Ffmpeg渲染工厂 by 焕奇灵动
 */

public class FfmpegRenderersFactory extends DefaultRenderersFactory {
    public static final int MODE_OFF=0;//软解
    public static final int MODE_ON=1;//硬解

    /**
     * @param context 上下文
     * @param DecodingMode 解码模式
     */
    public FfmpegRenderersFactory(Context context,int DecodingMode) {
        super(context);
        switch (DecodingMode){
            case MODE_OFF:
                setExtensionRendererMode(EXTENSION_RENDERER_MODE_OFF);//软解
                break;
            case MODE_ON:
                setExtensionRendererMode(EXTENSION_RENDERER_MODE_ON);//硬解
                setExtensionRendererMode(EXTENSION_RENDERER_MODE_PREFER);
                break;
        }

    }
    @Override
    protected void buildAudioRenderers(Context context,
                                       int extensionRendererMode,
                                       MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, AudioSink audioSink, Handler eventHandler, AudioRendererEventListener eventListener,
                                       ArrayList<Renderer> out) {
        out.add(new FfmpegAudioRenderer());
        super.buildAudioRenderers(context, extensionRendererMode, mediaCodecSelector, enableDecoderFallback, audioSink, eventHandler, eventListener, out);
    }
}