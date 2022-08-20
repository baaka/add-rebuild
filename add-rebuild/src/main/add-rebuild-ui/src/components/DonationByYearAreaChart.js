import * as React from 'react';
import Paper from '@mui/material/Paper';
import {
    Chart,
    ArgumentAxis,
    ValueAxis,
    AreaSeries,
    Title,
    Legend,
} from '@devexpress/dx-react-chart-material-ui';
import {styled} from '@mui/material/styles';
import {ArgumentScale, Animation} from '@devexpress/dx-react-chart';
import {
    curveCatmullRom,
    area,
} from 'd3-shape';
import {scalePoint} from 'd3-scale';

const PREFIX = 'Demo';

const classes = {
    chart: `${PREFIX}-chart`,
};
const Root = props => (
    <Legend.Root {...props} sx={{display: 'flex', margin: 'auto', flexDirection: 'row'}}/>
);
const Label = props => (
    <Legend.Label {...props} sx={{whiteSpace: 'nowrap'}}/>
);
const StyledChart = styled(Chart)(() => ({
    [`&.${classes.chart}`]: {
        paddingRight: '20px',
    },
}));

const Area = props => (
    <AreaSeries.Path
        {...props}
        path={area()
            .x(({arg}) => arg)
            .y1(({val}) => val)
            .y0(({startVal}) => startVal)
            .curve(curveCatmullRom)}
    />
);

const month = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];


export default class DonationByYearAreaChart extends React.PureComponent {
    constructor(props) {
        super(props);

        const donations = props['donations'];
        console.log(donations);

        const data = [];
        for (let m in month) {
            data.push(
                {month: month[m], usd: 0, eur: 0},
            )
        }

        for (let i in donations) {
            let item = donations[i];
            const m = month[new Date(item.creationTime).getMonth()];
            const found = data.find(element => element.month === m);
            if (item.currency === 'USD') {
                found.usd += item.amount;
            } else {
                found.eur += item.amount;
            }
        }

        this.state = {
            data,
        };
    }


    render() {
        const {data: chartData} = this.state;
        return (
            <Paper style={{padding: 30}}>
                <StyledChart
                    data={chartData}
                    className={classes.chart}
                >
                    <ArgumentScale factory={scalePoint}/>
                    <ArgumentAxis/>
                    <ValueAxis/>

                    <AreaSeries
                        name="USD"
                        valueField="usd"
                        argumentField="month"
                        seriesComponent={Area}
                    />
                    <AreaSeries
                        name="EUR"
                        valueField="eur"
                        argumentField="month"
                        seriesComponent={Area}
                    />
                    <Animation/>
                    <Legend position="bottom" rootComponent={Root} labelComponent={Label}/>
                    <Title text="Current Year Donations by Month"/>
                </StyledChart>
            </Paper>
        );
    }
}
