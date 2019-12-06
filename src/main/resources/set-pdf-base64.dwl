%dw 2.0
import * from dw::core::Binaries
var pdfBinary = payload
output text/plain
---
toBase64(pdfBinary)