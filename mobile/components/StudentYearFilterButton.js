import * as React from 'react';
import { StyleSheet} from 'react-native';
import { SegmentedButtons } from 'react-native-paper';

const StudentYearFilterButton = () => {
    const [value, setValue] = React.useState('e');

    return (
        <SegmentedButtons
            style={styles.buttons}
            value={value}
            onValueChange={setValue}
            buttons={[
                {
                    value: '2020',
                    label: '2020',
                },
                {
                    value: '2021',
                    label: '2021',
                },
                {
                    value: '2022',
                    label: '2022'
                },
            ]}
        />

    );
};

const styles = StyleSheet.create({
    buttons: {
        marginHorizontal: 30,
        marginTop: 30,
    }
});

export default StudentYearFilterButton;

