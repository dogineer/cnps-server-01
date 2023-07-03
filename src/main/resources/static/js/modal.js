function clipPreview(data) {
  var clip = data.parentElement;

  var clipId = clip.parentElement.getAttribute('data-clip-id')
  var clipTitle = clip.parentElement.getAttribute('data-clip-title')
  var clipPath = clip.parentElement.getAttribute('data-clip-path')

  var clipData = {
    "clip": {
      "id": clipId,
      "title": clipTitle,
      "path": clipPath
    }
  }

  var modal = document.getElementById("modal")
  var modalClipPreview = document.createElement("div")
  var videoTag = document.createElement("video")
  var source = document.createElement("source")

  modal.appendChild(modalClipPreview)
  modalClipPreview.appendChild(videoTag)
  videoTag.appendChild(source)

  modalClipPreview.setAttribute("class", "clip-preview")
  source.setAttribute("type", "video/mp4")
  source.setAttribute("src", "http://localhost:8081/streaming/" + clipData.clip.title)

}