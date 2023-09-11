import {ClipPreviewService} from "../service/ClipPreviewService.js";

export class ClipController {
    static showPreview(e) {
        ClipPreviewService.clipPreview(e);
    }

    static importPremierPro() {

    }

    static removeVideoElement(clipPreviewBodyElement){
        ClipPreviewService.removeVideoElement(clipPreviewBodyElement)
    }
}