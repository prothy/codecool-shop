import {makeStyles} from '@material-ui/core'

export default makeStyles(() => ({
    root: {
        maxWidth: '100%'
    },
    media: {
        paddingTop: '56.25%', // 16:9,
        marginTop: '30',
        backgroundPosition: 'center',
        backgroundSize: 'contain'
    },
    cardActions: {
        display: 'flex',
        justifyContent: 'flex-end',
    },
    cardContent: {
        display: 'flex',
        justifyContent: 'space-between',
    },
}))