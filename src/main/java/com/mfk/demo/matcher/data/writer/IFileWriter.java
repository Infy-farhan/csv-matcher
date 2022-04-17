package com.mfk.demo.matcher.data.writer;

import java.util.List;

import com.mfk.demo.matcher.matcher.Match;

public interface IFileWriter {
    public void write(String path, List<Match> matches) throws Exception;
}
