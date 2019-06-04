package com.app.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 保存文件到相应的路径，按版本号修改文件名
 * 
 * @author moxy
 *
 */
public class MyFile {

	// private MultipartFile fileSource;

	private String fileName;

	private String oldFileName;

	private int fileSizeMax = 200000000;

	private String path;

	private boolean flag = true;

	/**
	 * @param fileSource
	 *            源文件
	 * @param versionNo
	 *            版本号
	 * @param path
	 *            文件路径
	 * @param prefix
	 *            文件后缀
	 */
	public MyFile(MultipartFile fileSource, String versionNo, String path, String prefix) {
		if (fileSource != null && !fileSource.isEmpty()) {
			// 获取源文件名
			oldFileName = fileSource.getOriginalFilename();
			// 源文件的后缀
			String fileprefix = FilenameUtils.getExtension(oldFileName);
			this.path = path;

			Date currentTime = new Date(); // 获取当前时间
			String str = Long.toString(currentTime.getTime());
			str = str.substring(6, 10);
			String str2 = Long.toString(currentTime.getTime()).substring(4, 11);
			// 判断文件是否大于fileSizeMax
			if (fileSource.getSize() > this.fileSizeMax) {
				System.out.println(fileSource.getSize());
				flag = false;
			} else if (fileprefix.equalsIgnoreCase(prefix)) {
				System.out.println(prefix);
				// 判断文件名是否有版本号，
				// 截取文件名
				if (oldFileName.indexOf("com.") != -1) {
					if (oldFileName.indexOf("-V") == -1) {
						fileName = oldFileName.substring(0, oldFileName.indexOf(prefix)) + "-" + versionNo + "-" + str
								+ "." + prefix;
					} else {
						fileName = oldFileName.substring(0, oldFileName.indexOf("-V")) + "-" + versionNo + "-" + str
								+ "." + prefix;
					}
				} else if (oldFileName.indexOf("com.") == -1) {
					fileName = str2 + "." + prefix;
				}
				System.out.println(fileName);
				// 生成目标文件
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存上传文件
				try {
					fileSource.transferTo(targetFile);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("失败");
					flag = false;
				}
			}
		} else {
			System.out.println("文件为空");
		}
	}

	/**
	 * 获取修改后的文件名
	 * 
	 * @return
	 */
	public String getFileName() {
		if (flag) {
			return this.fileName;
		} else
			return null;
	}

	/**
	 * 获取修改后的文件路径
	 * 
	 * @return
	 */
	public String getPath() {
		if (flag) {
			return this.path + File.separator + this.fileName;
		} else
			return null;
	}

}
