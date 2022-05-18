package gasipan.controller.user;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
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
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attached/*")
@Slf4j
public class UserAttachedController {

	private final Environment environment;
	private final AttachedFileService attachedFileService;
	
	/**
	 * 파일업로드
	 * @param request
	 * @param response
	 * @return
	 */
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
	
    /**
     * 파일다운로드
     * @param request
     * @param response
     * @throws Exception
     */
	@PostMapping("/file/download")
	@ResponseBody
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileKey = request.getParameter("fileKey");
		String fileName = request.getParameter("fileName");
		String boardType = request.getParameter("boardType");
		String boardTypeStr = request.getParameter("boardTypeStr");
		String filePath = environment.getProperty("attached.upload.root") + "\\" + boardTypeStr;
		
		log.info("------down--------fileKey--------"+fileKey);
		log.info("------down--------fileName--------"+fileName);
		log.info("------down--------boardType--------"+boardType);
		log.info("------down--------downloadPath--------"+filePath);
		
		// 다운로드 대상을 파일객체로 생성
		File downFile = this.getFile(fileKey, filePath);

		// HTTP 응답 데이터형식 지정
		response.setContentType("application/octet-stream");
		// HTTP 응답 헤더 설정
		response.setHeader("Content-disposition", "attachment;filename=\""+this.korean2ascii(fileName));
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		try {
			
			byte readByte[] =  new byte[4096];
			FileInputStream fileinputStream = new FileInputStream(downFile);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileinputStream);
			
			int i;
			while((i = bufferedInputStream.read(readByte, 0, 4096)) != -1)
				servletOutputStream.write(readByte, 0, i);
			
			bufferedInputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
	}
    
	/**
	 * 파일이름 인코딩
	 * @param data
	 * @return
	 */
    public String korean2ascii(String data) {
        String rtn = null;
        try {
            rtn = (data == null) ? "" :
                new String(data.getBytes("euc-kr"), "8859_1");
        }
        catch (java.io.UnsupportedEncodingException e) {}
        return rtn;
    }
    
    /**
     * 파일 삭제
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/file/delete")
    @ResponseBody
    public Map<String, Object> deleteFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> rs = new HashMap<>();
    	
    	Boolean result = false;
    	AttachedFileDTO attachedFileDTO = new AttachedFileDTO();
    	
    	attachedFileDTO.setBoardType(request.getParameter("boardType"));
    	attachedFileDTO.setFileKey(request.getParameter("fileKey"));
    	attachedFileDTO.setFileName(request.getParameter("fileName"));
		String boardTypeStr = request.getParameter("boardTypeStr");
		String filePath = environment.getProperty("attached.upload.root") + "\\" + boardTypeStr;
		
		log.info("------delete--------fileKey--------"+attachedFileDTO.getFileKey());
		log.info("------delete--------fileName--------"+attachedFileDTO.getFileName());
		log.info("------delete--------boardType--------"+attachedFileDTO.getBoardType());
		log.info("------delete--------downloadPath--------"+filePath);
		
		// 파일 객체 생성
		File target = this.getFile(attachedFileDTO.getFileKey(), filePath);
		
		// DB에 저장되어있는 파일에 대한 정보 삭제
		attachedFileService.deleteAttachedFile(attachedFileDTO);
		// 파일 삭제
		result = target.delete();
		rs.put("code", result);
		
		return rs;
    }
    
}
