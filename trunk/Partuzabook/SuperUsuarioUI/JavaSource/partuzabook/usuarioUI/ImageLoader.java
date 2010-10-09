package partuzabook.usuarioUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ImageLoader {

    public void paintImage(OutputStream out, Object data) throws IOException {
    	
    	if (null == data) {
            return;
        }
    	
    	//HttpServletRequest request = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    	String requestContextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    	
    	//javax.faces.context.ExternalContext.getResourcePaths(String)
    	//System.out.println("Album.paintImage():: data="+data+" :: ContextPath="+requestContextPath);
    	
    	//FacesContext.getCurrentInstance().getExternalContext().getResource("/uploadRoot/0/(0).jpg")
    	//String data2 = FacesContext.getCurrentInstance().getExternalContext().encodeResourceURL(data.toString());
    	
    	InputStream in = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(
    			/*requestContextPath + File.separator + */data.toString());
    	
        //File imageResource = /*fileManager.*/getFileByPath(data.toString());
        //File imageResource = new File(requestContextPath, data.toString());
        //paintImageToBrowser(out, imageResource);

    	if (null == in) {
    		System.out.println("ImageLoader.paintImage()::: input stream for "+data+" is NULL");
            return;
        }
		System.out.println("ImageLoader.paintImage()::: input stream for "+data+" is OKAY");
    	paintImageToBrowser(out, in);
    }
    
    private void paintImageToBrowser(OutputStream out, InputStream in) throws IOException {
    	byte [] toWrite = new byte[8192];
    	try {
    		while(in.read(toWrite) != -1) {
        		out.write(toWrite);
    		}
    	} finally {
			in.close();
		}
    }

    private void paintImageToBrowser(OutputStream out, File imageResource) throws IOException {
        
        if (imageResource != null && imageResource.exists()) {
              	
        	byte [] toWrite = new byte[8192];
        	
        	FileInputStream  in = new FileInputStream(imageResource);

        	try {
        		while(in.read(toWrite) != -1) {
            		out.write(toWrite);
        		}
        	} finally {
				in.close();
			}
               	
        } else {
        	//String suffix = excludeFilePrefix(imageResource.getPath());
        	//paintImage(out, fileManager.transformPath(Constants.DEFAULT_ORIGINAL_PICTURE, suffix));
        	paintImage(out, "/img/defaultavatar.jpg");
			return;
        }
    }

	private String excludeFilePrefix(String path) {
		final int begin = path.lastIndexOf("_");
        final int end = path.lastIndexOf(".");
        return path.substring(begin, end);
	}
	
    public File getFileByPath(String path) {
	    return new File(path);
    }

}