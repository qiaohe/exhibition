package cn.mobiledaily.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reportService")
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {
}
