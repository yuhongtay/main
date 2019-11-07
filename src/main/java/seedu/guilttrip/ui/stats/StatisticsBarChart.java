package seedu.guilttrip.ui.stats;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.guilttrip.commons.core.LogsCenter;
import seedu.guilttrip.model.statistics.DailyStatistics;
import seedu.guilttrip.ui.UiPart;

/**
 * Displays the user's statistics by months in the form of a bar chart.
 */
public class StatisticsBarChart extends UiPart<Region> {
    private static final String FXML = "/statistics/StatisticsBarChart.fxml";
    private ObservableList<DailyStatistics> statsForDaily;
    private XYChart.Series<String, Number> expenseChart;
    private XYChart.Series<String, Number> incomeChart;
    private ObservableList<String> listOfDays;
    private ObservableList<XYChart.Data<String, Number>> dataForExpense;
    private ObservableList<XYChart.Data<String, Number>> dataForIncome;
    private final Logger logger = LogsCenter.getLogger(StatisticsPieChart.class);

    @FXML
    private BarChart barChart;

    @FXML
    private CategoryAxis dateOfRecord;

    @FXML
    private NumberAxis statisticsForDay;

    @FXML
    private VBox testView;

    @FXML
    private StackPane stackForBar;

    @FXML
    private Label noEntryLabel;

    public StatisticsBarChart(ObservableList<DailyStatistics> statsForDaily) {
        super(FXML);
        this.statsForDaily = statsForDaily;
        this.expenseChart = new XYChart.Series<String, Number>();
        this.incomeChart = new XYChart.Series<String, Number>();
        this.expenseChart.setName("Expenses");
        this.incomeChart.setName("Income");
        this.dataForExpense = FXCollections.observableArrayList();
        this.dataForIncome = FXCollections.observableArrayList();
        statsForDaily.addListener(new ListChangeListener<DailyStatistics>() {
            @Override
            public void onChanged(Change<? extends DailyStatistics> change) {
                updateBarChart(statsForDaily);
            }
        });
        barChart.setTitle("Statistics Daily Summary");
        barChart.setLegendSide(Side.BOTTOM);
        barChart.setAnimated(false);
        dateOfRecord.setLabel("Date");
        statisticsForDay.setLabel("Daily Expense and Income");
        this.listOfDays = FXCollections.observableArrayList();
        dateOfRecord.setCategories(listOfDays);
        updateBarChart(statsForDaily);
    }

    /**
     * Updates the PieChart displayed by running a calculation on every notified change from the original statsMaplist.
     * @param statsForDaily contains a listener that calls for this method.
     */
    public void updateBarChart(ObservableList<DailyStatistics> statsForDaily) {
        this.expenseChart.getData().clear();
        this.incomeChart.getData().clear();
        barChart.getData().clear();
        //
        this.listOfDays.clear();
        dataForIncome.clear();
        dataForExpense.clear();

        for (int i = 0; i < statsForDaily.size(); i++) {
            DailyStatistics t = statsForDaily.get(i);
            if (t.getTotalExpense() != 0.00) {
                this.dataForExpense.add(new XYChart.Data<String, Number>(Integer.toString(t.getDate()),
                        t.getTotalExpense()));
            }
            if (t.getTotalIncome() != 0.00) {
                this.dataForIncome.add(new XYChart.Data<String, Number>(Integer.toString(t.getDate()),
                        t.getTotalIncome()));
            }
        }
        if (dataForIncome.isEmpty() && dataForExpense.isEmpty()) {
            barChart.setVisible(false);
            noEntryLabel.setVisible(true);
        } else {
            int numberOfDays = statsForDaily.get(0).getNumberOfDays();
            for (int i = 1; i <= numberOfDays; i++) {
                listOfDays.add(Integer.toString(i));
            }

            ObservableList<XYChart.Series<String, Number>> seriesList = FXCollections.observableArrayList(expenseChart,
                    incomeChart);
            this.expenseChart.setData(dataForExpense);
            this.incomeChart.setData(dataForIncome);
            barChart.getData().addAll(seriesList);
            barChart.setVisible(true);
            noEntryLabel.setVisible(false);
        }
    }
}