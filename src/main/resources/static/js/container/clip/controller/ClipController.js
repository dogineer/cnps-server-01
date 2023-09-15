import {ClipPreviewService} from "../service/ClipPreviewService.js";
import {import_res} from "../service/ImportPremiereProService.js";

export class ClipController {
    static showPreview(e) {
        ClipPreviewService.clipPreview(e);
    }

    static importPremierPro(element) {
        import_res(element);
    }

    static removeVideoElement(clipPreviewBodyElement){
        ClipPreviewService.removeVideoElement(clipPreviewBodyElement)
    }
}