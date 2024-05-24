package com.jiabin.video.m3u8.practice.resource;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class VideoResource extends AbstractResource {

    private final String m3u8Content;
    private final List<Resource> tsResources;

    public VideoResource(String m3u8Content, List<Resource> tsResources) {
        this.m3u8Content = m3u8Content;
        this.tsResources = tsResources;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(m3u8Content).append("\n");

        for (Resource tsResource : tsResources) {
            sb.append(tsResource.getFilename()).append("\n");
            InputStream tsInputStream = tsResource.getInputStream();
            sb.append(StreamUtils.copyToString(tsInputStream, StandardCharsets.UTF_8));
            sb.append("\n");
        }

        return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getFilename() {
        return "combined.m3u8"; // 返回合并后的M3U8文件名
    }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
