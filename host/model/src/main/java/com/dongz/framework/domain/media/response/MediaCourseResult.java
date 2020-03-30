package com.dongz.framework.domain.media.response;

import com.dongz.framework.model.response.ResponseResult;
import com.dongz.framework.model.response.ResultCode;
import com.dongz.framework.domain.media.MediaFile;
import com.dongz.framework.domain.media.MediaVideoCourse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
@NoArgsConstructor
public class MediaCourseResult extends ResponseResult {
    public MediaCourseResult(ResultCode resultCode, MediaVideoCourse mediaVideoCourse) {
        super(resultCode);
        this.mediaVideoCourse = mediaVideoCourse;
    }

    MediaFile mediaVideo;
    MediaVideoCourse mediaVideoCourse;
}
