angular.module( 'daybook.localization', [] )
.factory( 'locales', function() {
    if ( navigator.language === 'en-US' ) {
        return {
            'common.name'     : 'Name',
            'common.count'    : 'Count',
            'common.price'    : 'Price',
            'common.comment'  : 'Comment',
            'common.add'      : 'Add',
            'common.complete' : 'Complete',
            'common.total'    : 'Total',
            'common.date'     : 'Date',

            // header
            'buylist.prev'    : 'Previous buy lists',
            'buylists'        : 'Buy Lists',
            'items'           : 'Items',
            'itemsStat'       : 'Statistics',

            // login panel
            'login'           : 'Login',
            'password'        : 'Password',
            'action.log.in'   : 'Log In',
            'action.sign.in'  : 'Sign In',
            'action.log.out'  : 'Log Out',

            // registration modal window
            'registration.title'            : 'New user Registration',
            'registration.login'            : 'Login',
            'registration.nickname'         : 'Nickname',
            'registration.password'         : 'Password',
            'registration.password.confirm' : 'Confirm password',

            'action.register'               : 'Register',
            'action.close'                  : 'Close',

            'registration.error.login'      : 'Please, enter login',
            'registration.error.nickname'   : 'Please, enter nickname',
            'registration.error.password'   : 'Please, enter password',
            'registration.error.password.confirm' : 'Please, confirm password',
            'registration.error.password.confirm.match' : 'Password and confirm must match'
        };
    } else if ( navigator.language === 'ru' ) {
        return {
            'common.name'     : 'Имя',
            'common.count'    : 'Количество',
            'common.price'    : 'Цена',
            'common.comment'  : 'Комментарий',

            'common.add'      : 'Добавить',
            'common.complete' : 'Завершить',
            'common.total'    : 'Всего',
            'common.date'     : 'Дата',

            // header
            'buylist.prev'    : 'Предыдущие листы покупок',
            'buylists'        : 'Листы покупок',
            'items'           : 'Товары',
            'itemsStat'       : 'Статистика',

            // login panel
            'login'           : 'Имя пользователя',
            'password'        : 'Пароль',
            'action.log.in'   : 'Вход',
            'action.sign.in'  : 'Регистрация',
            'action.log.out'  : 'Выход',

            // registration modal window
            'registration.title'            : 'Регистрация нового пользователя',
            'registration.login'            : 'Имя пользователя',
            'registration.nickname'         : 'Ник',
            'registration.password'         : 'Пароль',
            'registration.password.confirm' : 'Подтверждение пароля',

            'action.register'               : 'Завершить регистрацию',
            'action.close'                  : 'Закрыть',

            'registration.error.login'      : 'Пожалуйста, введите имя пользователя',
            'registration.error.nickname'   : 'Пожалуйста, введите ник',
            'registration.error.password'   : 'Пожалуйста, введите пароль',
            'registration.error.password.confirm' : 'Пожалуйста, подтвердите пароль',
            'registration.error.password.confirm.match' : 'Пароль и подтверждение должны совпадать'
        };
    }
} )
.directive( 'dbLocalize', function( locales ) {
    return {
        restrict: 'A',
        link: function( $scope, element, attrs ) {
            if ( locales[ attrs.dbLocalize ] )
                element.html( locales[ attrs.dbLocalize ] );
            else
                console.log( "Cannot find localized string for: " + attrs.dbLocalize );
        }
    }
} )
.directive( 'dbLocalizePlaceholder', function( locales ) {
    return {
        restrict: 'A',
        link: function( $scope, element, attrs ) {
            if ( locales[ attrs.dbLocalizePlaceholder ] )
                element.attr( 'placeholder', locales[ attrs.dbLocalizePlaceholder ] );
            else
                console.log( "Cannot find localized string for: " + attrs.dbLocalizePlaceholder );
        }
    }
} );