package gasipan.controller.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import gasipan.constant.GasipanConstructer;
import gasipan.dto.AttachedFileDTO;
import gasipan.service.AttachedFileService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attached/*")
public class UserAttachedController {

	private final Environment environment;
	private final AttachedFileService attachedFileService;
	
	@PostMapping("/file/upload")
	@ResponseBody
	public Map<String, Object> attacheFile(HttpServletRequest request, HttpServletResponse response) {
		
		String boardType = request.getParameter("boardType");
		String boardTypeStr = request.getParameter("boardTypeStr");
		String savePath = environment.getProperty("attached.upload.root") + "\\" + boardTypeStr;
		
		Map<String, Object> result = new HashMap<>();
		AttachedFileDTO attachedFileDTO = new AttachedFileDTO();
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
		
	    try {
	    	
			if(fileNames.hasNext()) {
				String fileObjName = (String)fileNames.next();
				MultipartFile formFile = multipartHttpServletRequest.getFile(fileObjName);
				
				if (formFile.getSize() > 0) {
					  // 파일이름 변환(평문 -> UUID)
					  String strFileKey = UUID.randomUUID().toString().toUpperCase();
					  String orgFileName = formFile.getOriginalFilename();
					  strFileKey = strFileKey + orgFileName.substring(orgFileName.lastIndexOf('.'));
					  
					  attachedFileDTO.setFileKey(strFileKey);
					  attachedFileDTO.setFileName(orgFileName);
					  attachedFileDTO.setBoardType(boardType);
					  attachedFileDTO.setMimeType(formFile.getContentType());
					  attachedFileDTO.setSize(formFile.getSize());

					  InputStream stream = null;
					  stream = formFile.getInputStream();

				       OutputStream bos = new FileOutputStream(this.getFile(strFileKey, savePath));

					   int bytesRead = 0;
					   byte[] buffer = new byte[8192];
				       while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				       	bos.write(buffer, 0, bytesRead);
					   }
					
					   bos.close();
					   stream.close();
					  
					  attachedFileService.registAttachedFile(attachedFileDTO);

					  
				} // if (formFile.getSize() > 0) {} end

			} // if(fileNames.hasNext()) {} end
			
			result.put("attachedFile", attachedFileDTO);
			result.put("resultCode", GasipanConstructer.RESULT_SUCCESS);
			
		} catch (Exception e) {
			result.put("resultCode", GasipanConstructer.ATTACHED_ERROR_CODE);
			result.put("message", e.getMessage());
		}
		
		return result;
	}
	
	
	/**
	 * 파일 키와 파일 패스를 이용하여 파일 객체를 얻어온다.
	 * @param fileKey
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
    private File getFile(String fileKey, String filePath) throws Exception {
    	String realFileName ="";
    	
        File temp = new File(filePath);
        if(!temp.isDirectory()) temp.mkdirs();
        
    	if (!filePath.endsWith("/")) {
    		realFileName = filePath + "/" + fileKey;
    	} else {
    		realFileName = filePath + fileKey;
    	}
    	File file = new File(realFileName);
    	return file;
    }	
	
	@PostMapping("/file/download")
	public void downloadFile() {
		
	}
	
}
