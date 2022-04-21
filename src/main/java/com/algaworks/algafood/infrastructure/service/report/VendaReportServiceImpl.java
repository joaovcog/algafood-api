package com.algaworks.algafood.infrastructure.service.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.repository.EstatisticaVendaRepository;
import com.algaworks.algafood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class VendaReportServiceImpl implements VendaReportService {

	@Autowired
	private EstatisticaVendaRepository estatisticaVendaRepository;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		List<VendaDiaria> vendasDiarias = estatisticaVendaRepository.consultarVendasDiarias(filtro, timeOffset);
		JRDataSource dataSource = new JRBeanCollectionDataSource(vendasDiarias);

		JasperPrint jasperPrint = null;

		try {
			jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possível emitir relatório de vendas diárias.", e);
		}
	}

}
