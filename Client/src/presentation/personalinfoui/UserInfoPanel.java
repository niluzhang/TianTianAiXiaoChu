package presentation.personalinfoui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

public class UserInfoPanel {

	ArrayList<String> daily_game_numbers;
	ArrayList<String> daily_average_scores;
	ArrayList<String> per_game_scores;
	int max_combo_number;
	int game_number;
	int average_score;
	int max_score;

	public JPanel userPanel(ArrayList<String> daily_game_numbers,
			ArrayList<String> daily_average_scores,
			ArrayList<String> per_game_scores, int max_combo_number,
			int game_number, int average_score, int max_score)
			throws FontFormatException, IOException {
		this.daily_game_numbers = daily_game_numbers;
		this.daily_average_scores = daily_average_scores;
		this.per_game_scores = per_game_scores;
		this.max_combo_number = max_combo_number;
		this.game_number = game_number;
		this.average_score = average_score;
		this.max_score = max_score;

		// 主背景
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 847, 381);
		panel.setBackground(Color.white);
		Image image = new ImageIcon(UserInfoPanel.class.getResource("/bin/573598.jpg")).getImage();
		JLabel l = new aLabel(image);
		l.setBounds(0, 0, 847, 381);

		// 字体设置

		// label设置
		final JLabel info_label = new JLabel("基础信息");
		final JLabel daily_game_number_label = new JLabel("每日局数曲线");
		final JLabel daily_score_label = new JLabel("每日平均得分曲线");
		final JLabel per_game_score_label = new JLabel("每局得分");

		info_label.setHorizontalAlignment(SwingConstants.CENTER);
		info_label.setFont(new Font("迷你简娃娃篆",Font.PLAIN,13));
		info_label.setBounds(5, 5, 122, 55);
		daily_game_number_label.setHorizontalAlignment(SwingConstants.CENTER);
		daily_game_number_label.setFont(new Font("迷你简娃娃篆",Font.PLAIN,13));
		daily_game_number_label.setBounds(5, 65, 122, 55);
		daily_score_label.setHorizontalAlignment(SwingConstants.CENTER);
		daily_score_label.setFont(new Font("迷你简娃娃篆",Font.PLAIN,13));
		daily_score_label.setBounds(5, 125, 122, 55);
		per_game_score_label.setHorizontalAlignment(SwingConstants.CENTER);
		per_game_score_label.setFont(new Font("迷你简娃娃篆",Font.PLAIN,13));
		per_game_score_label.setBounds(5, 185, 122, 55);

		// info_panel设置
		final JPanel info_panel = new JPanel();
		info_panel.setBounds(125, 5, 715, 371);
		final Border border = BorderFactory.createEtchedBorder(new Color(87,176,242),
				new Color(81,94,249));
		info_panel.setBorder(border);
		info_panel.setLayout(null);
		info_panel.setOpaque(false);
		info_panel.repaint();

		final JPanel basic_info_panel = basicInfo();
		final JPanel daily_game_number_panel = dailyGameNumber();
		final JPanel daily_average_scores_panel = dailyAverageScore();
		final JPanel per_game_scores_panel = perGameScore();

		info_label.setBorder(border);
		info_panel.add(basic_info_panel);
		info_label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				info_label.setFont(new Font("迷你简娃娃篆",Font.PLAIN,13));
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				info_label.setBorder(border);
				daily_game_number_label.setBorder(null);
				daily_score_label.setBorder(null);
				per_game_score_label.setBorder(null);

				info_panel.removeAll();
				info_panel.add(basic_info_panel);
				info_panel.revalidate();
				info_panel.repaint();
			}
		});
		daily_game_number_label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				daily_game_number_label.setBorder(border);
				info_label.setBorder(null);
				daily_score_label.setBorder(null);
				per_game_score_label.setBorder(null);

				info_panel.removeAll();
				info_panel.add(daily_game_number_panel);
				info_panel.revalidate();
				info_panel.repaint();
			}
		});
		daily_score_label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				daily_score_label.setBorder(border);
				info_label.setBorder(null);
				daily_game_number_label.setBorder(null);
				per_game_score_label.setBorder(null);

				info_panel.removeAll();
				info_panel.add(daily_average_scores_panel);
				info_panel.revalidate();
				info_panel.repaint();
			}
		});
		per_game_score_label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				per_game_score_label.setBorder(border);
				info_label.setBorder(null);
				daily_game_number_label.setBorder(null);
				daily_score_label.setBorder(null);

				info_panel.removeAll();
				info_panel.add(per_game_scores_panel);
				info_panel.revalidate();
				info_panel.repaint();
			}
		});

		panel.add(per_game_score_label);
		panel.add(daily_score_label);
		panel.add(daily_game_number_label);
		panel.add(info_label);
		panel.add(info_panel);
		panel.add(l);

		panel.setLayout(null);
		panel.revalidate();
		panel.repaint();
		return panel;
	}

	private JPanel basicInfo() throws FontFormatException, IOException {
		JPanel basic_info_panel = new JPanel();
		basic_info_panel.setBounds(3, 3, 709, 365);

		JLabel game_number_label = new JLabel("总局数：" + game_number);
		game_number_label.setFont(new Font("文鼎CS舒同体",Font.PLAIN,30));
		game_number_label.setBounds(260, 50, 280, 30);
		game_number_label.setVisible(true);

		JLabel average_score_label = new JLabel("平均得分：" + average_score);
		average_score_label.setFont(new Font("文鼎CS舒同体",Font.PLAIN,30));
		average_score_label.setBounds(260, 110, 280, 30);
		average_score_label.setVisible(true);

		JLabel max_score_label = new JLabel("最高分：" + max_score);
		max_score_label.setFont(new Font("文鼎CS舒同体",Font.PLAIN,30));
		max_score_label.setBounds(260, 170, 280, 30);
		max_score_label.setVisible(true);

		JLabel max_combo_number_label = new JLabel("最高连击次数：" + max_combo_number);
		max_combo_number_label.setFont(new Font("文鼎CS舒同体",Font.PLAIN,30));
		max_combo_number_label.setBounds(260, 230, 280, 30);
		max_combo_number_label.setVisible(true);

		basic_info_panel.setLayout(null);
		basic_info_panel.add(game_number_label);
		basic_info_panel.add(average_score_label);
		basic_info_panel.add(max_score_label);
		basic_info_panel.add(max_combo_number_label);
		basic_info_panel.setBackground(new Color(126, 234, 239, 122));
		// basic_info_panel.setOpaque(false);

		return basic_info_panel;
	}

	private JPanel dailyGameNumber() {
		ChartPanel frame1;
		JPanel daily_game_number_panel = new JPanel();
		daily_game_number_panel.setBounds(2, 2, 711, 367);

		XYDataset xydataset = createDataset(daily_game_numbers);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "日期",
				"局数", xydataset, false, true, false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xyplot.setBackgroundPaint(Color.white);// 设置网格背景颜色
		xyplot.setDomainGridlinePaint(Color.pink);// 设置网格竖线颜色
		xyplot.setRangeGridlinePaint(Color.pink);// 设置网格横线颜色
		xyplot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));// 设置曲线图与xy轴的距离，即曲线与xy轴贴近的距离
		xylineandshaperenderer.setBaseShapesVisible(true);// 设置曲线是否显示数据点
		xyplot.getRangeAxis().setStandardTickUnits(
				NumberAxis.createIntegerTickUnits());// 设置数据轴的刻度递进范围

		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("dd-MMM"));
		frame1 = new ChartPanel(jfreechart, false);

		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		// jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		daily_game_number_panel.setLayout(new GridLayout(1, 1));
		daily_game_number_panel.add(frame1);
		daily_game_number_panel.setBackground(new Color(1.0F, 0.75F, 0.0F,
				0.45F));
		// daily_game_number_panel.setOpaque(false);
		return daily_game_number_panel;
	}

	private JPanel dailyAverageScore() {
		JPanel daily_average_score_panel = new JPanel();
		daily_average_score_panel.setBounds(2, 2, 711, 367);
		ChartPanel frame1;

		XYDataset xydataset = createDataset(daily_average_scores);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "日期",
				"平均得分", xydataset, false, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xyplot.setBackgroundPaint(Color.white);// 设置网格背景颜色
		xyplot.setDomainGridlinePaint(Color.pink);// 设置网格竖线颜色
		xyplot.setRangeGridlinePaint(Color.pink);// 设置网格横线颜色
		xyplot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));// 设置曲线图与xy轴的距离，即曲线与xy轴贴近的距离
		xylineandshaperenderer.setBaseShapesVisible(true);// 设置曲线是否显示数据点

		xyplot.getRangeAxis().setStandardTickUnits(
				NumberAxis.createIntegerTickUnits());// 设置数据轴的刻度递进范围

		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("dd-MMM"));
		frame1 = new ChartPanel(jfreechart, false);
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		// jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		daily_average_score_panel.setLayout(new GridLayout(1, 2));
		frame1.setOpaque(false);
		daily_average_score_panel.add(frame1);
		daily_average_score_panel.setBackground(new Color(1.0F, 0.75F, 0.0F,
				0.45F));
		// daily_average_score_panel.setOpaque(false);
		return daily_average_score_panel;
	}

	private JPanel perGameScore() {
		JPanel per_game_score_panel = new JPanel();
		per_game_score_panel.setBounds(0, 0, 715, 371);

		String title[] = { "日期", "时间", "得分" };
		String tableInfo[][] = new String[per_game_scores.size()][3];
		for (int i = 0; i < per_game_scores.size(); i++) {
			String s = per_game_scores.get(i);
			String sData[] = s.split("#");
			tableInfo[i][0] = sData[1];
			tableInfo[i][1] = sData[2];
			tableInfo[i][2] = sData[0];
		}

		JPanel panel = setTable(tableInfo, title);
		per_game_score_panel.add(panel);
		per_game_score_panel.setBackground(new Color(1.0F, 0.75F, 0.0F, 0.45F));
		per_game_score_panel.setOpaque(false);
		return per_game_score_panel;
	}

	private static XYDataset createDataset(ArrayList<String> data) {
		int[] game_data = new int[data.size()];
		int[] day = new int[data.size()];
		int[] month = new int[data.size()];
		int[] year = new int[data.size()];
		for (int i = 0; i < data.size(); i++) {
			String s = data.get(i);
			String sData[] = s.split("#");
			game_data[i] = Integer.parseInt(sData[0]);
			String date[] = sData[1].split("-");
			day[i] = Integer.parseInt(date[2]);
			month[i] = Integer.parseInt(date[1]);
			year[i] = Integer.parseInt(date[0]);
		}

		@SuppressWarnings("deprecation")
		TimeSeries timeseries = new TimeSeries("",
				org.jfree.data.time.Day.class);
		for(int i = 0;i<data.size();i++){
			timeseries.add(new Day(day[i], month[i], year[i]), game_data[i]);
		}
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	private JPanel setTable(String[][] Info, String[] headTitle) {
		DefaultTableModel tableModel = new DefaultTableModel(Info, headTitle);
		JTable infoTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		infoTable.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setOpaque(false); // 将渲染器设置为透明
		// 将这个渲染器设置到fileTable里。这个设置在没有另外专门对column设置的情况下有效
		// 若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		// 因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		infoTable.setDefaultRenderer(Object.class, render);

		infoTable.getTableHeader().setReorderingAllowed(false);
		infoTable.setCellSelectionEnabled(true);
		infoTable.setFillsViewportHeight(true);
		infoTable.setGridColor(Color.white);
		infoTable.setEnabled(false);
		// infoTable.setShowHorizontalLines(false);
		// infoTable.setShowVerticalLines(false);
		infoTable.setRowHeight(30);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcr.setOpaque(false);
		infoTable.setDefaultRenderer(Object.class, tcr);

		JScrollPane panel7 = new JScrollPane();
		panel7.getViewport().setOpaque(false);// 将JScrollPane设置为透明
		panel7.setOpaque(false);// 将中间的viewport设置为透明
		panel7.setViewportView(infoTable);// 装在表格

		panel7.setPreferredSize(new Dimension(710, 371));
		panel7.setBackground(new Color(220, 220, 203));
		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(126, 234, 239, 122));
		// panel4.setOpaque(false);
		panel4.setBounds(2, 0, 713, 371);
		panel4.add(panel7, BorderLayout.CENTER);
		panel4.repaint();

		return panel4;
	}

	private class aLabel extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image image;

		public aLabel(final Image image) {
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, 847, 381, null);
		}
	}
}