package com.studymouse.studymouseserver.word;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/17
 */
class ArchiveTagTest {

    @Test
    void checkArchiveOpposite() {
        assertThat(ArchiveTag.NOT_ARCHIVE.getOppositionArchive()).isEqualTo(ArchiveTag.ARCHIVE);
        assertThat(ArchiveTag.ARCHIVE.getOppositionArchive()).isEqualTo(ArchiveTag.NOT_ARCHIVE);
    }
}