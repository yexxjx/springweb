package testfile.employeemanager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeefileService {
    private String baseDir=System.getProperty("user.dir");
    private String uploadDir=baseDir+"/build/resources/main/static/employee/";

    public String upload(MultipartFile uploadFile){
        if(uploadFile==null || uploadFile.isEmpty()==true){return null;}
        File uploadPath=new File(uploadDir);
        if(uploadPath.exists()==false){
            uploadPath.mkdir();
        }
        String uuid= UUID.randomUUID().toString();
        String fileName=uuid+uploadFile.getOriginalFilename().replaceAll("_","-");

        File uploadRealPath=new File(uploadDir+fileName);
        try{uploadFile.transferTo(uploadRealPath);
            return fileName;
        } catch (IOException e){System.out.println(e);}
        return null;
    }
}

