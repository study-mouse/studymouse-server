package com.studymouse.studymouseserver.word;

/**
 * Created by jyami on 2020/07/17
 */
public enum ArchiveTag {
    NOT_ARCHIVE,
    ARCHIVE;

    public ArchiveTag getOppositionArchive(){
        return this == NOT_ARCHIVE ? ARCHIVE : NOT_ARCHIVE;
    }
}
