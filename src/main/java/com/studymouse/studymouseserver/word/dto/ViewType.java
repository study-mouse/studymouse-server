package com.studymouse.studymouseserver.word.dto;

import com.studymouse.studymouseserver.word.ArchiveTag;

/**
 * Created by jyami on 2020/07/17
 */
public enum  ViewType {
    DASH_BOARD(ArchiveTag.NOT_ARCHIVE), ARCHIVE_BOARD(ArchiveTag.ARCHIVE);

    private final ArchiveTag archiveTag;

    ViewType(ArchiveTag archiveTag) {
        this.archiveTag = archiveTag;
    }

    public ArchiveTag getArchiveTag() {
        return archiveTag;
    }
}
