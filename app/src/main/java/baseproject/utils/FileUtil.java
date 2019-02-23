package baseproject.utils;

import android.os.Build;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class FileUtil {
	/**
	 * 判断文件路径是否合法
	 * @param name 文件名
	 * @return
	 */
	public static boolean isDirNameVaild(String name){
		if (name == null || name.isEmpty()) {
			return false;
		}

		if(name.contains("|") || name.contains(":") || name.contains("*") || name.contains("<")
				|| name.contains(">") || name.contains("\"") || name.contains("'") || name.contains("?")){
			return false;
		}

		return true;
	}

	/**
	 * 将文件剪切至指定路径
	 * @param srcFile	原文件
	 * @param dstDir  目标路径
	 * @throws Exception
	 */
	public static void cutTo(File srcFile,File dstDir) throws Exception{
		cutTo(srcFile, dstDir,srcFile.getName(),false);
	}

	/**
	 * 将文件剪切至指定路径
	 * @param srcFile	原文件
	 * @param dstDir  目标路径
	 * @param isCover  是否覆盖
	 * @throws Exception
	 */
	public static void cutTo(File srcFile,File dstDir,String dstName,boolean isCover) throws Exception{
		copyTo(srcFile, dstDir,dstName,isCover);
		srcFile.delete();
	}

	/**
	 * 将文件复制到指定目录
	 * @param srcFile 原文件
	 * @param dstDir 目标路径
	 * @throws Exception
	 */
	public static void copyTo(File srcFile,File dstDir) throws Exception{
		copyTo(srcFile, dstDir,srcFile.getName(),false);
	}

	/**
	 * 将文件复制到指定目录
	 * @param srcFile 原文件
	 * @param dstDir 目标路径
	 * @param isCover 是否覆盖
	 * @throws Exception
	 */
	public static void copyTo(File srcFile,File dstDir,String dstName,boolean isCover) throws Exception{
		if(!srcFile.isFile()){
			throw new IllegalArgumentException("srcFile is not a file");
		}


		if(!isDirNameVaild(dstDir.toString())){
			throw new IllegalArgumentException("dir name contains \\/:*?\"\'<>|");
		}


		if(!dstDir.exists()){
			dstDir.mkdirs();
		}

		try {
			File outputFile = new File(dstDir.getAbsolutePath() + File.separator + dstName);

			if(outputFile.exists() && !isCover){
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    throw new FileAlreadyExistsException("dst file is exist");
				}else{
					throw new Exception("dst file is exist");
				}
			}

			BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(srcFile));
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

			byte buffer[] = new byte[1024];
			int size;
			while((size = fileInputStream.read(buffer)) != -1){
				fileOutputStream.write(buffer,0,size);
				fileOutputStream.flush();
			}

			fileInputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
