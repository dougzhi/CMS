package com.dongz.framework.domain.media.response;

import com.dongz.framework.model.response.ResponseResult;
import com.dongz.framework.model.response.ResultCode;
import com.dongz.framework.domain.media.MediaFile;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mrt on 2018/3/31.
 */
@Data
@NoArgsConstructor
public class MediaFileResult extends ResponseResult {
    MediaFile mediaFile;
    public MediaFileResult(ResultCode resultCode, MediaFile mediaFile) {
        super(resultCode);
        this.mediaFile = mediaFile;
    }
}
